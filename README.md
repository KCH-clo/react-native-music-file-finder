
# react-native-music-file-finder

## Getting started

`$ npm install react-native-music-file-finder --save`

### Mostly automatic installation

`$ react-native link react-native-music-file-finder`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-music-file-finder` and add `RNMusicFileFinder.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNMusicFileFinder.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNMusicFileFinderPackage;` to the imports at the top of the file
  - Add `new RNMusicFileFinderPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-music-file-finder'
  	project(':react-native-music-file-finder').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-music-file-finder/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-music-file-finder')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNMusicFileFinder.sln` in `node_modules/react-native-music-file-finder/windows/RNMusicFileFinder.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Music.File.Finder.RNMusicFileFinder;` to the usings at the top of the file
  - Add `new RNMusicFileFinderPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNMusicFileFinder from 'react-native-music-file-finder';

// TODO: What to do with the module?
RNMusicFileFinder;
```
  