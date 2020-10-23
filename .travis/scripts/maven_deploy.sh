#!/usr/bin/env bash

# This should be called from repository root

mvn deploy \
--settings ./.travis/maven/maven-central-settings.xml \
--activate-profiles build-extras,sign-artifacts,import-env-code-signing-credentials,sonatype-ossrh-deployment
