# Top GitHub <img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/web_hi_res_120.png" width="110" align="left"> 
##### *Find what repositories the GitHub community is most excited about today.*
<br/>
<br/>
<br/>
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-main.png" width="210">
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-languages.png" width="210">
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-edit-languages.png" width="210">
<br/>
<br/>
##Features
#### API calls
**Top GitHub** uses [Retrofit](http://square.github.io/retrofit/) to process API requests from GitHub.  
The Retrofit **RetrofitRestadapter** is tied to the Application and created only one time, it comunicates the results of API calls using [Eventbus](https://github.com/greenrobot/EventBus).
<br/>

#### Vector drawables
Almost every Drawable used is generated from a Vector and thanks to [Wnafee Vector Compat](https://github.com/wnafee/vector-compat) they are even backward compatible to API 14+.  
The programming languages icons are courtesy of [DevIcon](http://devicon.fr/): I converted the SVGs to XML Vectors with the help of [inloop](http://inloop.github.io/svg2android/).  
You can find the available languages [here](https://github.com/mmazzarolo/top-github/blob/master/app/src/main/res/values/arrays.xml): as you can see the list is massive and there are only [a few vectors](https://github.com/mmazzarolo/top-github/tree/master/app/src/main/res/drawable) for them... I'll be pleased to add a vector if you can find one that suits the app (in SVG or XML Vector format).
<br/>

#### Data storage
Saving personal settings (like the languages) on a DB was a little overkill so I opted for saving them in SharedPreferences (with the help of [EasyDatastore](https://github.com/fdoyle/EasyDatastore])).
<br/>
<br/>

## Notes
This app is totally free, you can do whatever you want to it.  
Please, let me know if you find any bug!
I also plan to:
* Use a MVVM pattern with [the new Android Data Binding](https://developer.android.com/tools/data-binding/guide.html) to bind the views to the Recyclerviews
* Add more options and vectors to the app  
<br/>  
<br/>  

<a href="https://play.google.com/store/apps/details?id=com.mmazzarolo.dev.topgithub">
  <img alt="Get it on Google Play"
       src="https://developer.android.com/images/brand/en_generic_rgb_wo_45.png" />
</a>
