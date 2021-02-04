#!/usr/bin/env bash

# This should be called from repository root

if [[ "$(./.github-actions/scripts/get_version.sh)" == *-SNAPSHOT ]]; then
  if [[ $1 == release ]]; then
    >&2 echo "Cannot deploy in release mode when version is snapshot"
    exit 1;
  fi
else
  if [[ $1 != release ]]; then
    >&2 echo "Cannot deploy in non-release mode when version is not snapshot"
    exit 1;
  fi;
fi


function deploy() {
  maven_profiles=build-extras,sign-artifacts,import-env-code-signing-credentials,"$1"-deployment
  if [[ $1 == release ]]; then
    maven_profiles="${maven_profiles},automatic-central-release"
  fi
  echo "Using maven profiles: [${maven_profiles}]"

  mvn deploy -s ./.github-actions/maven/sonatype-ossrh-settings.xml --activate-profiles "$maven_profiles" -B -V
}
