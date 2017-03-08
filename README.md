# FloatOnKeyboardLayout

FloatOnKeyboardLayout is a ViewGroup that auto push up when soft keyboard popups.


## Features
- Make soft keyboard can not overlap you views.
- Only one simple class, not dependency other libs

# Gradle Dependency

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'im.ll:floatlayout:1.0.0'
}


## Sample Usage
In the layout xml files, simply add FloatOnKeyboardLayout as root element. There is only one child in FloatOnKeyboardLayout.  

```xml
<github.ll.view.FloatOnKeyboardLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
	
	<...other views
	...>
</github.ll.view.FloatOnKeyboardLayout>

```


