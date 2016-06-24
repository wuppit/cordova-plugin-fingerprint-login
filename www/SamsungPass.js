function SamsungPass() {
}

TouchID.prototype.isAvailable = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "isSamsungPassSupported", []);
};

TouchID.prototype.didFingerprintDatabaseChange = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "hasRegisteredFingers", []);
};

TouchID.prototype.verifyFingerprint = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "startIdentify", []);
};


SamsungPass.install = function () {
  if (!window.plugins) {
    window.plugins = {};
  }

  window.plugins.SamsungPass = new SamsungPass();
  return window.plugins.SamsungPass;
};

cordova.addConstructor(SamsungPass.install);