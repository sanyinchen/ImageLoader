# ImageLoader
##Describe 
Hi,guys,this is a simple ImageLoader with caches.It can dowonload a image via your config.There are three status(onSuccess,onProcess,onFail).I hope the lib can help you in anyways. 
##USAGES
	DownloadConfig downloadConfig = new DownloadConfig(testurl);
	ImageLoader imageLoader = new ImageLoader(downloadConfig);
	imageLoader.build(new DefaultImageDownloadListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                // do something
            }
            @Override
            public void onProcess(long process, long total, long perCent) {
                // do something
            }
            
            @Override
            public void onFail() {
                // do something
            }
        });
	imageLoader.startDownload();
	
It's very simply,yeah..you are right.

##Summary  
* If you have any question, welcome feedback.
* If you have any question,please email to me(My email:sanyinchen@gmail.com)
* Welcome to subscribe my [google+](https://plus.google.com/u/0/100465464266192894461)  
