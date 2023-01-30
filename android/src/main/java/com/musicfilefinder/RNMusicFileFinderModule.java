
package com.musicfilefinder;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

public class RNMusicFileFinderModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNMusicFileFinderModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNMusicFileFinder";
  }
  
  @ReactMethod
  public void getAllMusics(Promise promise) {
    String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
    };

    Cursor cursor = getCurrentActivity().getContentResolver().query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            "is_music != 0",
            null,
            null);

    List<String> songs = new ArrayList<String>();
    String ss = "";
    try{
      while(cursor.moveToNext()){
          // songs.add(String.join(',', cursor));
          ss += String.join(',', cursor);
      }
    }
    promise.resolve(ss);
    catch (Exception e){
      promise.reject("", e);
    }
  }
}