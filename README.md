# bypasses
Repackaging of [Bypass](https://github.com/Uncodin/bypass) with additional features

[![Build Status](https://travis-ci.org/Commit451/bypasses.svg?branch=master)](https://travis-ci.org/Commit451/bypasses) [![](https://jitpack.io/v/Commit451/bypasses.svg)](https://jitpack.io/#Commit451/bypasses)

## Gradle Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Then, add the library to your project `build.gradle`
```gradle
dependencies {
    compile 'com.github.Commit451:bypasses:latest.release.here'
}
```

This library is provided as a "fat" aar with native binaries for all available architectures. To
reduce your APK size, use the ABI filtering/splitting techniques in the Android plugin:
http://tools.android.com/tech-docs/new-build-system/user-guide/apk-splits

## Usage
See the [bypass docs](http://uncodin.github.io/bypass/)

Additional features include:
- Image click detection
```java
bypass.setImageSpanClickListener(new ImageSpanClickListener() {
    @Override
    public void onImageClicked(View view, ImageSpan imageSpan, String url) {
        Snackbar.make(root, "Image clicked with url: " + url, Snackbar.LENGTH_LONG)
                .show();
    }
});
```
See the sample for more.

## Image Loading
Image loading can be done via the `ImageGetter` interface. Libraries exist for [Picasso](https://github.com/Commit451/BypassPicassoImageGetter) and [Glide](https://github.com/Commit451/BypassGlideImageGetter).

## Robolectric
See [this issue](https://github.com/Commit451/bypasses/issues/2) for an explanation for getting Robolectric to work.

## Proguard
This dependency also packages the Proguard rules [suggested](https://github.com/Uncodin/bypass/issues/195) for bypass to work properly with Proguard enabled

License
--------

    Copyright 2017 Commit 451
    Copyright 2015 Uncodin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

