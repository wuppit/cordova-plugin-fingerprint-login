package com.susanne.fingerprint.SamsungPass

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;
import com.samsung.android.sdk.pass.SpassInvalidStateException;


public class SamsungPass extends CordovaPlugin {
	
	private SpassFingerprint mSpassFingerprint;
    private Spass mSpass;
	private boolean isFeatureEnabled_fingerprint = false;
	private boolean hasRegisteredFinger = false;
	private Context mContext;
	private final String TAG = "SamsungPass Cordova";
	
	public SamsungPass() {
	}
	
	
	@Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
		mContext = this.cordova.getActivity().getApplicationContext();
		sSpass = new Spass();

        try {
            sSpass.initialize(mContext);
        } catch (SsdkUnsupportedException e) {
            Log.e(TAG, "Exception: " + e);
        } catch (UnsupportedOperationException e) {
            Log.e(TAG, "Fingerprint Service is not supported in the device");
        }
		
		isFeatureEnabled_fingerprint = sSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);

        if (isFeatureEnabled_fingerprint) {
            mSpassFingerprint = new SpassFingerprint(mContext);
            Log.i(TAG, "Fingerprint Service is supported in the device.");
            Log.i(TAG, "SDK version : " + sSpass.getVersionName());
        } else {
            Log.e(TAG, "Fingerprint Service is not supported in the device.");
            return;
        }
		
		isFeatureEnabled_index = sSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT_FINGER_INDEX);
        isFeatureEnabled_custom = sSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT_CUSTOMIZED_DIALOG);
        isFeatureEnabled_uniqueId = sSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT_UNIQUE_ID);
        isFeatureEnabled_backupPw = sSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT_AVAILABLE_PASSWORD);
    }
	
    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
		if ("isSamsungPassSupported".equals(action)) {
            isSamsungPassSupported(args, callbackContext);
        }
		else if ("hasRegisteredFingers".equals(action)){
			hasRegisteredFingers(args, callbackContext);
		}
		else if ("startIdentify".equals(action)){
		}
		else{
            return false;
        }
        return true;
    }
	
	private void isSamsungPassSupported(JSONArray args, CallbackContext callbackContext) {
        Log.i(TAG, "Method: isSamsungPassSupported");

        if (isFeatureEnabled_fingerprint) {
            callbackContext.success();
        } else {
            callbackContext.error("Error: Feature not enable");
        }
    }
	
	private void hasRegisteredFingers(JSONArray args, CallbackContext callbackContext) {
        Log.i(TAG, "Method: hasRegisteredFingers");
		
		hasRegisteredFinger = mSpassFingerprint.hasRegisteredFinger();
		
        if (hasRegisteredFinger) {
            callbackContext.success();
        } else {
            callbackContext.error("Error: No fingerprints are registered");
        }
    }
	
	private void startIdentify(JSONArray args, CallbackContext callbackContext) {
		//TODO
	}
}
