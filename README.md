# react-native-music-file-finder

1. get all music files and metadata in device.
2. can get cover image.

This package currently supports android only.

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

## Methods

getAllMusics(needImage: boolean)
if needImage is true, return the image`s path.
