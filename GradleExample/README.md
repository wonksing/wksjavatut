# How to make it work

Initialize gradle project
```
$ gradle init
```

Choose basic(option 1) when it asks

---

Make some directories which holds source codes and resources
```
$ mkdir -p src/main/java/com/wksjava/tut/gradleexample
$ mkdir -p src/main/resources
$ mkdir -p src/test/java/com/wksjava/tut/gradleexample
$ mkdir -p src/test/resources
```

Edit build.gradle file as the following
```
plugins {
    id 'java'
    id 'application'
}

application {
    mainClassName = 'com.wksjava.tut.gradleexample.App'
    applicationDefaultJvmArgs = ['-Dconfig.path=configs']
}

sourceCompatibility = '1.8'
targetCompatibility = '1.8'
version = '1.2.1'


sourceSets {
    main {
         java {
            srcDirs = ['src/main/java']
         }
    }

    test {
        java {
            srcDirs = ['src/test/java']
        }
    }
}

jar {
  manifest {
    attributes(
      'Class-Path': configurations.compile.collect { it.getName() }.join(' '),
      'Main-Class': 'com.wksjava.tut.gradleexample.App'
    )
  }
}
```