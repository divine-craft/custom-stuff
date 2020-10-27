#!/usr/bin/env bash

# This should be called from repository root

maven_profiles=build-extras,sign-artifacts,import-env-code-signing-credentials
echo "Param = $1"
if [[ $1 == release ]]; then
  maven_profiles=${maven_profiles}",sonatype-ossrh-deployment-auto-release"
fi

mvn deploy --settings ./.travis/maven/maven-central-settings.xml --activate-profiles "${maven_profiles}"
