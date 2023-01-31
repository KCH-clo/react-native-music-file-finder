# react-native-music-file-finder

1. get all music files and metadata in device.
2. can get cover image.

This package currently supports android only.

![preview3](https://user-images.githubusercontent.com/70503548/215699566-f9517bd6-0fd9-4019-baba-a248e6d70b02.jpg)

## Getting started

`$ yarn add react-native-music-file-finder`

Add the permission in AndroidManifest.xml.

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

## Usage

```javascript
import RNMusicFileFinder from "react-native-music-file-finder";

useEffect(() => {
  const getMusicFiles = async () => {
    const musics = await RNMusicFileFinder.getAllMusics(true);
    for (const music of musics) {
      console.log(music);
    }
  };
  getMusicFiles();
}, []);
```

## Functions

getAllMusics(needImage?: boolean)
if needImage is true, return the image`s path.

## Example

https://github.com/KCH-clo/react-native-simple-audio-player#readme
