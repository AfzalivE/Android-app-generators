'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');

/**
 * Use fs.copy or fs.copyTpl depending on whether
 * the source begins with an underscore (_).
 *
 * @param source the source folder or file
 * @param destination the destination folder or file
 * @param props the props to pass on to copyTpl
 */
function copy(source, destination, props = this.props) {
  var splitSource = source.split("/")
  var fileName = splitSource[splitSource.length - 1]
  if (fileName.startsWith("_")) {
    this.fs.copyTpl(this.templatePath(source), this.destinationPath(destination), props)
  } else {
    this.fs.copy(this.templatePath(source), this.destinationPath(destination))
  }
}

function copyTpl(source, destination, props = this.props) {
  this.fs.copyTpl(this.templatePath(source), this.destinationPath(destination), props)
}

module.exports = class extends Generator {
  initializing() {
    console.log("templatePath: " + this.templatePath())
    console.log("destinationPath: " + this.destinationPath())
    console.log("contextRoot: " + this.contextRoot)
    this.destinationRoot(this.contextRoot)
    console.log("destinationPath: " + this.destinationPath())

    this.copy = copy.bind(this);
    this.copyTpl = copyTpl.bind(this);
  }

  prompting() {
    // Have Yeoman greet the user.
    this.log(
      yosay(`Welcome to the superb ${chalk.red('Android Kotlin MVVM')} generator!`)
    );

    const prompts = [{
        name: 'appName',
        message: 'What are you calling your app?',
        store: true,
        default: this.appname // Default to current folder name
      },
      {
        name: 'packageName',
        message: 'What package will you be publishing the app under?',
        store: true
      },
      {
        name: 'targetSdk',
        message: 'What Android SDK will you be targeting?',
        store: true,
        default: 28 // Android 9.0 (Oreo)
      },
      {
        name: 'minSdk',
        message: 'What is the minimum Android SDK you wish to support?',
        store: true,
        default: 19 // Android 4.4 (KitKat)
      },
      {
        name: 'useCI',
        message: 'Add .yml configs and Android license files for Gitlab and CircleCI',
        store: true,
        default: true
      },
      {
        name: 'fabricApiKey',
        message: 'Set a Fabric API Key to Add Fabric.io dependencies',
        store: false,
        default: ""
      }
    ];

    var injection = chalk.yellow("Injection")
    var remtData = chalk.magenta("Remote Data")
    var sr1 = chalk.magenta("Source")
    var lclData = chalk.green("Local Data")
    var sr2 = chalk.green("Source")
    var repositoryWithInMemoryCache = chalk.red("REPOSITORY (+ in-memory cache)")
    var frgmt = chalk.green("Fragment")
    var viewmdl = chalk.redBright("View Model")
    var observeLiveDataState = chalk.cyan("Observes LiveData State")
    var activ = chalk.yellow("Activity")

    console.log("Here is a little schema of the MVVM Clean Architecture")
    console.log(`
      ${injection}
+-------------------------------------------+      +----------------------------------------+
| +------------------+  +-----------------+ |      | +--------------+                       |
| |                  |  |                 | |      | |              |                       |
| |    ${remtData}   |  |   ${lclData}    | |      | |   ${frgmt}   |                       |
| |      ${sr1}      |  |     ${sr2}      | |      | |              |                       |
| |                  |  |                 | |      | +--+---------+-+                       |
| +---------+--------+  +--------+--------+ |      |    |         ^                         |
|           ^                    ^          |      |    |         | ${observeLiveDataState} |
|           |                    |          |      |    |         |                         |
|           |                    |          |      |    |         |                         |
|           |                    |          |      |    |         |                         |
|           |                    |          |      |    |         |                         |
| +---------+--------------------+--------+ |      | +--v---------+-+                       |
| |                                       | |      | |              |                       |
| |     ${repositoryWithInMemoryCache}    <----------+  ${viewmdl}  |                       |
| |                                       | |      | |              |              ${activ} |
| +---------------------------------------+ |      | +--------------+                       |
+-------------------------------------------+      +----------------------------------------+
`)

    return this.prompt(prompts).then(props => {
      // To access props later use this.props.someAnswer;
      this.props = props;
    });
  }

  projectFiles() {
    // project files
    this.copy('gitignore', '.gitignore');
    this.copy('gradle.properties', 'gradle.properties');
    this.copy('gradlew', 'gradlew');
    this.copy('gradlew.bat', 'gradlew.bat');
    this.copy('settings.gradle', 'settings.gradle');
    this.copy('gradle', 'gradle');

    this.copy('_build.gradle', 'build.gradle')
  }

  appFiles() {
    var packageDir = this.props.packageName.replace(/\./g, '/');

    this.copy('app/proguard-rules.pro', 'app/proguard-rules.pro');
    this.copy('app/_build.gradle', 'app/build.gradle');

    this.copy('app/src/main/_AndroidManifest.xml', 'app/src/main/AndroidManifest.xml');
    this.copyTpl('app/src/main/java', 'app/src/main/java/' + packageDir);
    this.copyTpl('app/src/main/res', 'app/src/main/res');
  }

  writing() {
    this.projectFiles()
    this.appFiles()
  }

  install() {
    this.installDependencies({
      bower: false
    });
  }
};
