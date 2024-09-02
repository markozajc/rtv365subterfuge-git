# RTV 365 (TV) Subterfuge
A project that ports the [RTV 365
(TV)](https://play.google.com/store/apps/details?id=si.rtvslo.rtv4dandroidtv_ab)
application to Android TV 7 (and hopefully earlier). While the application
installs correctly, playing anything fails due to a SSL error. The video
streaming domain, vodstr.rtvslo.si, uses Let's Encrypt certificates,
but the new ISRG Root X2 certificate is not trusted by Android 7.1.1
and above.

Fortunately, the entire application happens to be a webview to
https://androidtv.rtvslo.si/. Android's webview allows us to handle
(and ignore!) TLS errors. This makes the application prone to MiTM
attacks, but I haven't found a way to extract the root certificate from
[SslError](https://developer.android.com/reference/android/net/http/SslError).
The application supports accounts, but they're only used to gate access
to media - I don't think enabling MiTM is too large of a risk, but you're
welcome to submit patches if you've found a way around this.

The aim of this project is exactly that - to create a minimal webview
wrapper for the application and ignore certificate errors. If your version
of Android TV is not supported, please submit patches to marko@zajc.tel.

## Downloading
Ready-made builds are available on:
* https://files.zajc.tel/public/builds/rtv365tv-subterfuge

## Compiling
```
$ export ANDROID_HOME=/path/to/android/sdk JAVA_HOME=/path/to/jdk
$ ./gradlew assemble
```

## Available on
* https://git.zajc.tel/rtv365tv-subterfuge.git
* https://github.com/markozajc/rtv365tv-subterfuge
