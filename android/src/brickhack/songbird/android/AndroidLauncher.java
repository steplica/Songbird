package brickhack.songbird.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import brickhack.songbird.SongBird;

public class AndroidLauncher extends AndroidApplication {

    public native void DoFFT(double[] data, int size);

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SongBird(), config);

        System.loadLibrary("fft-jni");
	}
}
