This plugin can be used to connect the artifact management functionality of
Maven with an external build system. Artifacts are always ZIP files from the
`native-XXX` packaging types. Each ZIP file then has the following structure:

  * `bin/` - architecture independent, or architecture implied, binaries (such as executables/scripts)
  * `bin-XXX/` - architecture `XXX` specific binaries (such as executables/scripts)
  * `lib/` - architecture independent, or architecture implied, static libraries
  * `lib-XXX/` - architecture `XXX` specific static libraries
  * `include/` - header files and other resources needed to consume the package
  * `./` - anything else (such as license files)

This folder structure abstraction makes it possible to unzip an artifact and
simply update appropriate environment variables (for example LIB, INCLUDE, PATH)
to have it consumed by the build system. Similarly the outputs from arbitrary
build layouts (for example a Visual Studio Solution) are handled by configuring
the plugin to search for files in the right locations.
