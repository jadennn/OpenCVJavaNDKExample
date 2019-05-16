# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

#show build config
set(CMAKE_VERBOSE_MAKEFILE on)
#set libs dir
set(libs "${CMAKE_SOURCE_DIR}/libs")
#set include dir
include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/include)

#add libopencv_java4
add_library(libopencv_java4 SHARED IMPORTED )
#set libopencv_java4 path
set_target_properties(libopencv_java4 PROPERTIES
    IMPORTED_LOCATION "${libs}/${ANDROID_ABI}/libopencv_java4.so")

#add cpp path
aux_source_directory(src/main/cpp/source SOURCE_DIR)
#add native-lib
add_library( native-lib SHARED ${SOURCE_DIR})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib
                       android
                       log
                       libopencv_java4
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )