
package com.musicfilefinder;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.media.MediaMetadataRetriever;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.net.Uri;
import android.content.ContentUris;

import java.io.File;
import java.io.FileOutputStream;

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

  // @ReactMethod
  // public void getPathFromUri(String uri, Promise promise) {
  //   try {
  //     String[] projection = { MediaStore.Images.Media.DATA };
    
  //     Uri contentUri = Uri.parse(uri);
  //     Cursor cursor = getCurrentActivity().getContentResolver().query(
  //       contentUri, 
  //       projection, 
  //       null, 
  //       null, 
  //       null);
  //     cursor.moveToNext();
  //     String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
    
  //     promise.resolve(path);
  //   } catch (Exception e) {
  //     promise.reject("", e);
  //   }
  // }
   
  // @ReactMethod
  // public void getUriFromPath(String filePath, Promise promise) {
  //   try {
  //     Cursor cursor = getCurrentActivity().getContentResolver().query(
  //       MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
  //       null, 
  //       "_data = '" + "/storage/emulated/0/Download/KakaoTalk_20221229_120022213.mp4" + "'", 
  //       null, 
  //       null);
    
  //     cursor.moveToNext();
  //     int id = cursor.getInt(cursor.getColumnIndex("_id"));

  //     promise.resolve(id);
  //   } catch (Exception e) {
  //     promise.reject("", e);
  //   }
  // }
  
  @ReactMethod
  public void getAllMusics(boolean needImage, Promise promise) {
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

    WritableArray results = new WritableNativeArray();
    try {
      while (cursor.moveToNext()) {
        try {
          WritableMap musicInfo = Arguments.createMap();
          MediaMetadataRetriever retriever = new MediaMetadataRetriever();

          String id = cursor.getString(0);
          String path = cursor.getString(3);

          retriever.setDataSource(path);

          musicInfo.putString("id", id);
          musicInfo.putString("artist", cursor.getString(1));
          musicInfo.putString("title", cursor.getString(2));
          musicInfo.putString("path", path);
          musicInfo.putString("fileName", cursor.getString(4));
          musicInfo.putString("duration", cursor.getString(5));
          musicInfo.putString("genre", retriever.extractMetadata(retriever.METADATA_KEY_GENRE));

          if (needImage) {
            FileOutputStream fos = null;

            try {
              byte[] embedPic = retriever.getEmbeddedPicture();

              if (embedPic != null) {
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length);
                String imagePath = Environment.getExternalStorageDirectory() + "/" + id + ".jpg";
                File outFile = new File(imagePath);

                fos = new FileOutputStream(outFile);
                fos.write(embedPic);

                musicInfo.putString("imagePath", imagePath);
              }
            } catch (Exception e) {}
            finally {
              try {
                fos.close();
              } catch (Exception e) {}
            }
          }
          retriever.release();
          results.pushMap(musicInfo);
        }
        catch (Exception e) {}
      }
      promise.resolve(results);
    }
    catch (Exception e) {
      promise.reject("", e);
    }
  }
}