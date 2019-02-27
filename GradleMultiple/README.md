
vi build.gradle

```
subprojects {
    apply plugin: "java"

    repositories {
        jcenter()
    }

    sourceCompatibility = 1.8
    targetCompatibility = 1.8

    dependencies {
        testCompile "junit:junit:4+"
    }
}
```

vi settings.gradle

```
include ":sub1"
include ":sub2"
include ":sub3"

rootProject.name = "GradleMultiple"
```

```
$ mkdir -p sub1/src/main/java/com/wksjava/tut/gradlemultiple/sub1
$ mkdir -p sub1/src/main/resources
$ mkdir -p sub1/src/test/java/com/wksjava/tut/gradlemultiple/sub1
$ mkdir -p sub1/src/test/resources
$ mkdir -p sub2/src/main/java/com/wksjava/tut/gradlemultiple/sub2
$ mkdir -p sub2/src/main/resources
$ mkdir -p sub2/src/test/java/com/wksjava/tut/gradlemultiple/sub2
$ mkdir -p sub2/src/test/resources
$ mkdir -p sub3/src/main/java/com/wksjava/tut/gradlemultiple/sub3
$ mkdir -p sub3/src/main/resources
$ mkdir -p sub3/src/test/java/com/wksjava/tut/gradlemultiple/sub3
$ mkdir -p sub3/src/test/resources
```