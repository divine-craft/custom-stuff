#!/usr/bin/env bash

# This should be called from repository root

# Import encrypted code_signing.asc file
# shellcheck disable=SC2154
openssl aes-256-cbc -K "$encrypted_0dccf756cfe1_key" -iv "$encrypted_0dccf756cfe1_iv" \
-in ./.travis/gpg/code_signing.asc.enc -out ./.travis/gpg/code_signing.asc -d

gpg2 --alow-secret-key-import --batch --passphrase="${CODE_SIGNING_GPG_KEY_PASSPHRASE}" \
--keyring "${TRAVIS_BUILD_DIR}/pubring.gpg" --no-default-keyring --import ./.travis/gpg/code_signing.asc
