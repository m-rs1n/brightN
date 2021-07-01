package com.google;

import java.util.*;
import java.util.stream.Collectors;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private Video play;
  private boolean pause = false;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  private String videoDetail(Video video, HashMap<Object, Object> flags) {
    return String.format("%s (%s) [%s]",
            video.getTitle(),
            video.getVideoId(),
            video.getTags().stream().reduce(
                    (t, s) -> t + " " + s
            ).orElse(""))
            + (flags.containsKey(video.getVideoId())
            ? (" - FLAGGED (reason: " + flags.get(video.getVideoId()) + ")") : "");
  }

  public void showAllVideos() {
    //System.out.println("showAllVideos needs implementation");
    System.out.println("Here is a list of available video.");
    ArrayList<Video> videos = (ArrayList<Video>) videoLibrary.getVideos();
    for (int i = 0; i < videos.size(); i++) {
      Video video = videos.get(i);
      System.out.println(video.getTitle() + " (" + video.getVideoId() + ")" + video.getTags());
    }

  }

  private void stopVideoIfPlaying() {
    if (play != null) {
      System.out.printf("Stopping video: %s%n", play.getTitle());
    }
    play = null;
  }

  private void playNewVideo(Video video, HashMap<Object, Object> flags) {
    assert video != null;
    String videoId = video.getVideoId();
    if (flags.containsKey(videoId)) {
      System.out.printf("Cannot play video: Video is currently flagged (reason: %s)%n",
              flags.get(videoId));
      return;
    }
    System.out.printf("Playing video: %s%n", video.getTitle());
    play = video;
    pause = false;
  }

  public void playVideo(String videoId, HashMap<Object, Object> flags) {
    Video video = videoLibrary.getVideo(videoId);
    if (video != null) {
      stopVideoIfPlaying();
      playNewVideo(video, flags);
    } else {
      System.out.println("Cannot play video: Video does not exist");
    }
  }

  public void stopVideo() {
    if (play != null) {
      System.out.printf("Stopping video: %s%n", play.getTitle());
      play = null;
    } else {
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  Random generator = new Random();

  public void playRandomVideo(HashMap<Object, Object> flags) {
    stopVideoIfPlaying();
    List<Video> videos = videoLibrary.getVideos().stream()
            .filter(x -> !flags.containsKey(x.getVideoId())).collect(Collectors.toList());
    if (videos.isEmpty()) {
      System.out.println("No videos available");
      return;
    }
    int index = generator.nextInt(videos.size());
    playNewVideo(videos.get(index), flags);
  }



  public void pauseVideo() {
    if (play == null) {
      System.out.println("Cannot pause video: No video is currently playing");
    } else {
      String videoName = play.getTitle();
      if (pause) {
        System.out.printf("Video already paused: %s%n", videoName);
      } else {
        System.out.printf("Pausing video: %s%n", videoName);
        pause = true;
      }
    }
  }

  public void continueVideo() {
    if (play == null) {
      System.out.println("Cannot continue video: No video is currently playing");
    } else {
      String videoName = play.getTitle();
      if (pause) {
        System.out.printf("Continuing video: %s%n", videoName);
        pause = false;
      } else {
        System.out.println("Cannot continue video: Video is not paused");
      }
    }
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }

  public void showPlaying() {
  }
}