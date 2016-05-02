# Intro to Android Part 2

## Trying New IDE

Coba semua buka Android Studio, dan buat ulang project fun fact kita minggu
lalu menggunakan Android Studio. Terus di run, apakah semua bisa? Kalo bisa,
mulai hari ini kita akan pake Android Studio, karena yang sekarang di
support oleh google. Eclipse udah tidak di support lagi. Tapi kalo ngga
bisa, ya terpaksa kita pake Eclipse. Tapi saya sarankan untuk tugas dan
lain-lain kalo dikerjakan di laptop atau pc di rumah bisa pake android
studio saja.

## onCreate

Sampai dimana kita minggu lalu? Minggu lalu kita udah buat satu screen kan
ya. Mari kita buka project-nya. Ok, nah sekarang kita akan melanjutkan
project ini sampai selesai. Sekarang buka file source java-nya.


```java
public class FunFactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);
    }
}
```

Nah, seperti yang sudah saya sampaikan minggu lalu, di Android tidak ada
`public static void main`, yang ada `onCreate`. `onCreate` ini adalah method
yang pertama kali dijalankan ketika sebuah class dieksekusi.

Di line berikutnya ada `setContentView(R.layout.activity_fun_facts)` yang
berfungsi untuk men-set content dari screen kita dengan sebuah activity yang
sudah kita definiskan di file xml dengan nama `activity_fun_facts`. Kalo
misalnya kita bikin activity baru, kita juga bisa menggunakan activity
tersebut dengan memanggilnya dari line ini.

Misalnya kita buat activity baru, `File > New > Layout resource file` kita
namakan `activity_new`. Terus kita panggil file `activity_new` ini di source
code kita.

```java
setContentView(R.layout.activity_new)
```

Dan yang ke-load adalah activity baru. Cukup jelas ya?!

Mari kita lanjutkan. Kita mau buat supaya button berfungsi kalo di klik. Kita harus mengkoneksikan elemen-elemen yang ada di xml file tadi ke java file. Caranya kita akan gunakan method `findViewById`.

Pertama-tama kita harus mendeklarasikan elemen-elemennya terlebih dahulu.

```java
private Button mButton;
```

Kita juga harus import `Button`. Di Android Studio kita bisa lakukan dengan
Ctrl + Enter.

Seteleh mendefinisikan button, kita tinggal mengkoneksikan antara id button
yang di xml dengan yang ada di file Java yang baru kita buat ini. Kita
lakukan dibawah `setContentView`. Semua code kita harus berada dibawah
`setContentView` kalo ngga, si java ngga tau kita pake layout yang mana dan
ngga bisa ketemu button yang mau kita koneksikan, dan akan error pastinya.

```java
mButton = findViewById(R.id.button);
```

Tapi ada error nih. Kenapa? Karena `findViewById` akan me-return view.
Sementara variable kita tipe nya button. Button juga view sih, button adalah
turunan dari view. Tapi tidak compatible. Makanya tipe nya harus kita ubah
atau di cast. Tinggal tambahin `(Button)` aja setelah `=`.

Ok, sekarang silakan kalian definisikan `TextView` yang ditengah.


## On Click

Nah sekarang kita akan buat button-nya berfungsi. Caranya adalah dengan
mendefinisikan on click listener. Setelah itu kita set listener-nya ke
button.


```java
mButton = (Button) findViewById(R.id.button);
mTextView = (TextView) findViewById(R.id.textView);

View.OnClickListener listener = new View.OnClickListener() {
  @Override
    public void onClick(View v) {

    }
};

mButton.setOnClickListener(listener);
```

Tinggal kita tambahkan apa yang mau kita lakukan pada saat button di click di method `onClick`.

```java
public void onClick(View v) {
  String fact = "Ostriches can run faster than horses";
  mTextView.setText(fact);
}
```

Saatnya kita coba. Tinggal di-run aja.


## Random Facts

Ok, selanjutnya kita akan membuat mekanisme random-nya ketika button di
klik. Untuk awal, kita akan mulai dengan random angka dulu, nanti baru kita
buat beberapa fakta dalam bentuk array yang akan di random.


```java
public void onClick(View v) {
  String fact = "";
  // Randomly select a fact
  Random randomGenerator = new Random();
  int randomNumber = randomGenerator.nextInt(3);
  fact = randomNumber + "";

  //Update the screen with our dynamic fact
  mTextView.setText(fact);
}
```

## Randomize Array

Sekarang tugas kalian untuk membuat array of string untuk fakta-fakta yang
akan di random. Setelah buat array, setiap kali kita klik button, kita akan
dapat fakta yang di random dari array tadi. Silakan.


```java
public void onClick(View v) {
  String[] facts = {
    "Ants stretch when they wake up in the morning.",
    "Ostriches can run faster than horses.",
    "Olympic gold medals are actually made mostly of silver.",
    "You are born with 300 bones; by the time you are an adult you will have 206.",
    "It takes about 8 minutes for light from the Sun to reach Earth.",
    "Some bamboo plants can grow almost a meter in just one day.",
    "The state of Florida is bigger than England.",
    "Some penguins can leap 2-3 meters out of the water.",
    "On average, it takes 66 days to form a new habit.",
    "Mammoths still walked the earth when the Great Pyramid was being built." };

  // The button was clicked, so update the fact TextView with a new fact
  String fact = "";
  // Randomly select a fact
  Random randomGenerator = new Random();
  int randomNumber = randomGenerator.nextInt(facts.length);
  fact = facts[randomNumber];

  //Update the screen with our dynamic fact
  mTextView.setText(fact);
}
```

Tugas berikutnya, buatlah supaya fakta pertama yang muncul, yang pertama
kali tampil ketika app nya dijalankan supaya ngga sama, alias random.


## Refactor

Ada yang tau apa itu refactor?

Refactor itu semacam bersih-bersih code, bukan mengubah flow dari app, tapi membuat code lebih efisien dan mudah dipahami.

Code kita sekarang udah cukup bagus, udah bisa jalan. Tapi misalnya nih,
kita buat activity baru yang juga menggunakan fakta random seperti class
ini, kita harus membuat class baru dan akan banyak code yang berulang kan
ya? Dan kalo di class pertama ada logic yang berubah, kita harus copy paste
juga ke class kedua. Hal ini ngga bagus, istilah kerennya DRY: Don't repeat
yourself.

Karena itu kita butuh refactor untuk memastikan code kita tidak berulang.
Code yang berulang dapat kita bentuk menjadi sebuah komponen yang dapat
digunakan berkali-kali. Inilah yang akan kita lakukan. Kita akan buat sebuah
class yang isinya fakta-fakta yang nanti bisa kita gunakan berulang-ulang.
Kita namakan saja class-nya dengan `FactBook`. Buat class baru `File > New > Java Class`. Isinya adalah array of string fakta-fakta aja.

```java
import java.util.Random;

public class FactBook {
  public String getFact() {
    String[] facts = {
      "Ants stretch when they wake up in the morning.",
      "Ostriches can run faster than horses.",
      "Olympic gold medals are actually made mostly of silver.",
      "You are born with 300 bones; by the time you are an adult you will have 206.",
      "It takes about 8 minutes for light from the Sun to reach Earth.",
      "Some bamboo plants can grow almost a meter in just one day.",
      "The state of Florida is bigger than England.",
      "Some penguins can leap 2-3 meters out of the water.",
      "On average, it takes 66 days to form a new habit.",
      "Mammoths still walked the earth when the Great Pyramid was being built." };

    // The button was clicked, so update the fact TextView with a new fact
    String fact = "";
    // Randomly select a fact
    Random randomGenerator = new Random();
    int randomNumber = randomGenerator.nextInt(facts.length);
    fact = facts[randomNumber];

    return fact;
  }
}
```

Jadi kita buat sebuah class dengan satu method `getFact()` yang akan
me-return fakta random. Cukup jelas ya sampe sini?!

## Changing The Background Color

Ok, sekarang kita akan ganti background color-nya random juga. Cara
mengganti background, sama kayak yang kita lakukan di UI designer minggu
lalu, yaitu dengan menggunakan properti background color di level layout.
Untuk itu mari kita definisikan layout-nya di code kita dulu.

```java
private RelativeLayout mRelativeLayout;

// ..

```

Selanjutnya kita koneksikan pake `findViewById`. Tapi kok ngga ada id-nya?
Nah, kita harus set dulu id-nya di activity xml nya biar bisa kita
koneksikan ke file source java kita dengan menambahkan
`android:id="@+id/relativeLayout"`. Save dan kembali ke file java kita:

```java
mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
```

Sekarang id nya muncul. Nah, tinggal kita ganti warnanya pada saat button di
klik.

```java
public void onClick(View v) {

  //Update the screen with our dynamic fact
  String fact = mFactBook.getFact();
  mTextView.setText(fact);
  mRelativeLayout.setBackgroundColor(Color.RED);
}
```

## Adding More Color

Ok, sekarang kita akan me-random warna setiap kali button di click. Kita
bisa langsung random di main java file kita, tapi sekalian biar rapi kita
akan buat class yang akan meng-handle warna jadi di main class kita, kita
tinggal panggil getColor, sama seperti kita getFact tadi.

Buat class baru dengan nama ColorWheel dan buat sebuah method dengan nama
getColor().


```java
package com.elixirdose.funfacts;

import java.util.Random;

public class ColorWheel {

  private String[] mColors = {
    "#39add1", // light blue
    "#3079ab", // dark blue
    "#c25975", // mauve
    "#e15258", // red
    "#f9845b", // orange
    "#838cc7", // lavender
    "#7d669e", // purple
    "#53bbb4", // aqua
    "#51b46d", // green
    "#e0ab18", // mustard
    "#637a91", // dark gray
    "#f092b0", // pink
    "#b7c0c7"  // light gray
  };

  // Methods - Actions the object can take
  public String getColor() {
    String color = "";

    Random randomGenerator = new Random();
    int randomNumber = randomGenerator.nextInt(mColors.length);
    color = mColors[randomNumber];

    return color;
  }
}
```

Sekarang kita akan panggil class ini dari main class kita.

```java
private ColorWheel mColorWheel = new ColorWheel();

// ...

mRelativeLayout.setBackgroundColor(mColorWheel.getColor())
```

Wah, ternyata error. Jadi background color berharap integer, tapi getColor
me-return string. Nah kita harus buat supaya getColor me-return integer,
bukan string kalo gitu.

Buka lagi class ColorWheel dan ubah getColor-nya.

```java
public int getColor() {
  String color;
  // Randomly select a fact
  Random randomGenerator = new Random();
  int randomNumber = randomGenerator.nextInt(mColors.length);
  color = mColors[randomNumber];
  int colorAsInt = Color.parseColor(color);

  return colorAsInt;
}
```

## Testing And Debugging

Salah satu skill yang krusial yang dibutuhkan developer apps adalah
kemampuan untuk troubleshoot ketika terjadi masalah atau error. Kita akan
mempelajari tiga teknik untuk testing dan debugging:

1. Toast notification
2. Android log
3.

### Toast Notification

Toast notification adalah notifikasi di layar android kita yang munculnya
sebentar. Cocok untuk menampilkan informasi atau cek value dari variable
yang tidak membutuhkan input dari user. Teknik ini sangat sederhana, dan
bisa dijalankan dengan satu line code saja. Mari kita lihat caranya.

```java
Toast.makeText(FunFactsActivity.this, "This is a toast", Toast.LENGTH_SHORT).show();
mButton.setOnClickListener(listener);
```

Ok, kenapa method `makeText` bisa langsung dipanggil tanpa harus bikin
instance dulu? Karena methodnya static.

### Android Log

Cara berikutnya untuk men-debug saat app kita sedang running adalah dengan
menggunakan Android Log. Kita set log di code kita kemudian kita bisa ngecek di IDE kita apa yang terjadi. Mari kita lihat gimana caranya. Ada 5 tipe log yang bisa kita pake: lihat di docs: `Log.v(), .d(), .i(), .w(), .e()`.

Pertama, kita log di code-nya.
