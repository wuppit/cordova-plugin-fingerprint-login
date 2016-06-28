function SamsungPass() {
}

SamsungPass.prototype.isAvailable = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "isSamsungPassSupported", []);
};

SamsungPass.prototype.hasRegisteredFingers = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "hasRegisteredFingers", []);
};

SamsungPass.prototype.getFingerprintName = function (successCallback, errorCallback) {
  cordova.exec(successCallback, errorCallback, "SamsungPass", "getFingerprintName", []);
};

SamsungPass.prototype.startIdentify = function (successCallback, errorCallback) {
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