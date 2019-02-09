'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');

module.exports = class extends Generator {
  prompting() {
    // Have Yeoman greet the user.
    this.log(
      yosay(`Welcome to the superb ${chalk.red('Android Kotlin MVVM')} generator!`)
    );

    const prompts = [{
        name: 'name',
        message: 'What are you calling your app?',
        store: true,
        default: this.appname // Default to current folder name
      },
      {
        name: 'package',
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

  writing() {
    // project files
    // var fabricMavenUrl = "maven { url 'https://maven.fabric.io/public' }"
    // var fabricClasspath = "classpath 'io.fabric.tools:gradle:1.+'"

    var fabricMavenUrl = ""
    var fabricClasspath = ""

    this.fs.copy(this.templatePath('dummyfile.txt'), this.destinationPath('dummyfile.txt'))
    this.fs.copyTpl(this.templatePath('build.gradle'), this.destinationPath('build.gradle'), {
      fabricMavenUrl: fabricMavenUrl,
      fabricClasspath: fabricClasspath
    })
  }

  install() {
    this.installDependencies({
      bower: false
    });
  }
};
