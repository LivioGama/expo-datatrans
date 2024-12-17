const fs = require('fs');
const path = require('path');

const gradlePath = path.join(__dirname, '../android/build.gradle');
const gradleContent = fs.readFileSync(gradlePath, 'utf8');

const updatedContent = gradleContent.replace(
    /(allprojects\s*{\s*repositories\s*{)([\s\S]*?)(}\s*})/,
    `$1
        maven {
            // All of React Native (JS, Obj-C sources, Android binaries) is installed from npm
            url(new File(['node', '--print', "require.resolve('react-native/package.json')"].execute(null, rootDir).text.trim(), '../android'))
        }
        maven {
            // Android JSC is installed from npm
            url(new File(['node', '--print', "require.resolve('jsc-android/package.json', { paths: [require.resolve('react-native/package.json')] })"].execute(null, rootDir).text.trim(), '../dist'))
        }

        google()
        mavenCentral()
        maven { url 'https://www.jitpack.io' }
        maven { url 'https://datatrans.jfrog.io/artifactory/mobile-sdk/' }
    }`
);

fs.writeFileSync(gradlePath, updatedContent);
