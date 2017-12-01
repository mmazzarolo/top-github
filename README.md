# Top GitHub <img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/web_hi_res_120.png" width="110" align="left" /> 
Find what repositories the GitHub community is most excited about today.   

&nbsp;  
&nbsp;  
&nbsp;  
<p align="center">
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-main.png" width="210" />
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-languages.png" width="210" />
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/screen-edit-languages.png" width="210" />
</p>

<p align="center"><a href="https://play.google.com/store/apps/details?id=com.mmazzarolo.dev.topgithub&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" width="200" /></a>
</p>

## Disclaimer
<img src="https://raw.githubusercontent.com/mmazzarolo/top-github/master/extras/takend-down.png" />
Sadly, after ~3 years of service Top Github has been taken down from the Play Store because because it was violating the impersonation policy.  

The violation might have been caused by the app logo or by the app name.   

Honestly, since Top Github as always been an open source app (published on GitHub itself) and a side project, I never tought that the naming/logo would have been an issue.  
I already tried contacting GitHub but sadly they cannot do anything about.   

P.S.: I'm not blaming anyone, I admit that I should have been more careful when choosing the name/logo :)

## Features
#### API calls
**Top GitHub** uses [Retrofit](http://square.github.io/retrofit/) to process API requests from GitHub.  
The Retrofit **RetrofitRestadapter** is tied to the Application and created only one time, it comunicates the results of API calls using [Eventbus](https://github.com/greenrobot/EventBus).

#### Vector drawables
Almost every Drawable used is generated from a Vector and thanks to [Wnafee Vector Compat](https://github.com/wnafee/vector-compat) they are even backward compatible to API 14+.  
The programming languages icons are courtesy of [DevIcon](http://devicon.fr/): I converted the SVGs to XML Vectors with the help of [inloop](http://inloop.github.io/svg2android/).  
You can find the available languages [here](https://github.com/mmazzarolo/top-github/blob/master/app/src/main/res/values/arrays.xml): as you can see the list is massive and there are only [a few vectors](https://github.com/mmazzarolo/top-github/tree/master/app/src/main/res/drawable) for them... I'll be pleased to add a vector if you can find one that suits the app (in SVG or XML Vector format).

#### Data storage
Saving personal settings (like the languages) on a DB was a little overkill so I opted for saving them in SharedPreferences (with the help of [EasyDatastore](https://github.com/fdoyle/EasyDatastore])).

## Notes
This app is totally free, you can do whatever you want to it.  
Please, let me know if you find any bug!

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
