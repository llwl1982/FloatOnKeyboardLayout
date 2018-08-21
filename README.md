# FloatOnKeyboardLayout

FloatOnKeyboardLayout is a ViewGroup that auto push up when soft keyboard popups. Only one simple class, not dependency other libs


## Features
- Cancel limit of only one child view.
- Add function of anchor
- Add function of margin  

## Gradle Dependency

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'im.ll:floatlayout:1.1.0'
}
```

## Sample Usage
In the layout xml files, simply add 

```xml
<?xml version="1.0" encoding="utf-8"?>
<github.ll.view.FloatOnKeyboardLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:id="@+id/root_view">

    <LinearLayout
        android:id="@+id/child_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

        <LinearLayout
            android:id="@+id/anchor"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
        </LinearLayout>

    </LinearLayout>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@id/child_view"/>


</github.ll.view.FloatOnKeyboardLayout>

```

```Java
FloatOnKeyboardLayout floatOnKeyboardLayout = (FloatOnKeyboardLayout) findViewById(R.id.root_view);
        floatOnKeyboardLayout.setAnchor(findViewById(R.id.anchor));

        floatOnKeyboardLayout.setPopupListener(new FloatOnKeyboardLayout.OnKeyboardPopupListener() {
            @Override
            public void onKeyboardPopup(boolean isPop) {
                Toast.makeText(MainActivity.this, "popup: " + isPop, Toast.LENGTH_SHORT).show();
            }
        });

        floatOnKeyboardLayout.setMarginKeyboard(100);
```


