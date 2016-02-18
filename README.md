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
  <a href="https://play.google.com/store/apps/details?id=com.mmazzarolo.dev.topgithub&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" width="200" /></a>
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
<br/>  
<br/>

## Changelog
**v1.3**
- Added a "Last year" option (thanks to Aaron E. for the suggestion) 
- Fixed the "try again" button on error

**v1.2**
- Added "Share repository" and "Copy URL to clipboard" options on repository long click 
- Fixed a bug not showing the JavaScript icon
- Added Erlang icon

**v1.1**  
- Fixed a bug showing the wrong name in the toolbar after changing languages
- Fixed the selected period name on first install
- Fixed the error msg screen 
- Fixed naming convention and vector icons
