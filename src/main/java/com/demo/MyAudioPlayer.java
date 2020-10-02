package com.demo;

import org.springframework.util.ResourceUtils;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2020-10-2.
 */
public class MyAudioPlayer {


    private URL url = null; // 音乐文件的URl
    private AudioStream audioStream = null; // 播放器
    public MyAudioPlayer() {
        try {
            File file = ResourceUtils.getFile("classpath:templates/布.wav");
            url = file.toURI().toURL();
            InputStream inputStream = url.openStream(); // 获得音乐文件的输入流
            audioStream = new AudioStream(inputStream);
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用AudioPlayer静态成员player.start播放音乐
     */
    public void play() {
        AudioPlayer.player.start(audioStream);
    }

    public static void main(String[] args) {
        new MyAudioPlayer().play();
    }
}
