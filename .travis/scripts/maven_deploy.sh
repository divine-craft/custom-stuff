#!/usr/bin/env bash

# This should be called from repository root

maven_profiles=build-extras,sign-artifacts,import-env-code-signing-credentials
if [[ $1 == release ]]; then
  echo "Deploying in release mode"
  maven_profiles=${maven_profiles}",sonatype-ossrh-deployment-auto-release"
else
  echo "Deploying in release mode"
fi
echo "Using maven profiles: [${maven_profiles}]"

mvn deploy --settings ./.travis/maven/settings.xml --activate-profiles "${maven_profiles}"
