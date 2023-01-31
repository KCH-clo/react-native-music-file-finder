import { NativeModules } from "react-native";
import { MusicInfo } from "./interfaces/MusicInfo";

const { RNMusicFileFinder } = NativeModules;

/**
 * This function returns all audio files` info in this device.
 */
export async function getAllMusics(needImage?: Boolean): Promise<MusicInfo[]> {
  return RNMusicFileFinder.getAllMusics(Boolean(needImage));
}
