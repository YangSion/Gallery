/***************在以下模块中添加依赖***************/
plugins {
    id("kotlin-kapt")
}

dependencies {
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")
}
