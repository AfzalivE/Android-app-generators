#!/bin/bash

# Fix the CircleCI path
function getAndroidSDK(){
  export PATH="$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH"

  DEPS="$ANDROID_HOME/installed-dependencies"
# TODO check if specific version folder exists, if not then download that
  if [ ! -e $DEPS ]; then
    echo "Existing Android SDK not found, downloading..."
    echo y | android update sdk --no-ui --all --filter tools,platform-tools,build-tools-25.0.2,android-24,extra-google-m2repository,extra-google-google_play_services,extra-android-support
    touch $DEPS
  fi
}