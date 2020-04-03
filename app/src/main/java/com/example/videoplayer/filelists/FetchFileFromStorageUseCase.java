package com.example.videoplayer.filelists;

import android.content.Context;
import android.database.Cursor;
import android.database.Observable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.videoplayer.screen.common.BaseObservable;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FetchFileFromStorageUseCase extends BaseObservable<FetchFileFromStorageUseCase.Listener> {
    public interface Listener {
        void notifyFailfetchingFiles(String errorMessage);
        void notifySuccessfetchingFiles(List<FileItemModel> files);
    }
    List<String> supportedExtensions=new ArrayList(Arrays.asList(new String[]{
            "mp4","vob","3gp","mkv","webm","flv","avi","mov"
            ,"wmv","m4v","mpg","mts","ts","mpeg","3gp","3gpp","f4v"
    }));
    private Context context;
    public FetchFileFromStorageUseCase(Context context){
        this.context = context;
    }
    public void fetchDirectoryAndNotify(Context context,File directory){
        if(directory==null || !directory.exists()){
            for(Listener listener:getListeners()){
                listener.notifyFailfetchingFiles("Null directory");
            }
            return;
        }
        File[] directories=directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isHidden() &&( file.isDirectory() || isValidFile(file.getName()));
                // String name=pathname.getName();
                //String extension=name.substring(name.lastIndexOf('.')+1);
                // Log.d("debugging_filelist",pathname.getName());
                // return (extension!=null && supportedExtensions.contains(extension.toLowerCase()));
            }
        });
        List<FileItemModel> fillies=getFileAsModels(context,directories);

        for(Listener listener:getListeners()){
            listener.notifySuccessfetchingFiles(fillies);
        }
    }

    private List<FileItemModel> getFileAsModels(Context context, File[] directories) {
        List<FileItemModel> list=new ArrayList<>();
        if (directories==null)return list;
        for(File file:directories){
            FileItemModel model=generateModel(context,file);
            if(model!=null)
                list.add(model);
        }
        Collections.sort(list, new Comparator<FileItemModel>() {
            @Override
            public int compare(FileItemModel x, FileItemModel y) {
                return Boolean.compare(y.isDirectory,x.isDirectory);
            }
        });
        return list;
    }

    /**
     *
     *
     * @param context
     * @param file VideoFile
     * @return VideoFile pojo class
     */
    @Nullable
    private FileItemModel generateModel(Context context, File file) {
        if(file==null || !file.exists() )return null;

        return file.isDirectory() ? geneRateDirectoryModel(file) : file.isFile()?generateFileModel(file):null;
    }

    private FileItemModel generateFileModel(File file) {
        Log.d("dbging",file.getName());

        FileItemModel model=new FileItemModel(file.getName(),file.getAbsolutePath(),file.isDirectory());
        model.setLength(file.length());

//        MediaMetadataRetriever retriever=new MediaMetadataRetriever();
       /* try{
            retriever.setDataSource(context, Uri.parse(file.getPath()));
            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            model.setDuration(duration);
            String rotation = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
            model.setVideoRotation(rotation);
            //String size=retriever.extractMetadata(MediaMetadataRetriever.KE)
            Log.d("debugging","duration of file "+ file.getName() +" is "+duration);
            retriever.release();
        }catch (RuntimeException e){ }finally {
            if(retriever!=null)
                retriever.release();
        }*/

        return model;
    }

    private FileItemModel geneRateDirectoryModel(File file) {
        return new FileItemModel(file.getName(),file.getAbsolutePath(),file.length(),file.isDirectory());
    }

    private boolean isValidFile(String name) {
        if(name==null )return false;
        int index=name.lastIndexOf('.');
        return index<0 || ++index>=name.length() ? false : supportedExtensions.contains(name.substring(index).toLowerCase());

    }


    public void fetchFileListAndNotify(){
        List<FileItemModel> mItems = new ArrayList<>();
        String[] projection = {
                MediaStore.Video.VideoColumns.DATA ,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATE_ADDED,
                MediaStore.Video.Media.DATE_MODIFIED,
                MediaStore.Video.Media.RESOLUTION
        };
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{
                FileItemModel model =new FileItemModel();
                model.setName( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME))  );
                model.setPath( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))  );
                model.setAdded( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED))  );
                model.setModified( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED))  );
                model.setResolution( cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION))  );
                mItems.add(model);
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            for(Listener listener:getListeners()){
                listener.notifyFailfetchingFiles(e.getMessage());
            }

        }
        ArrayList<FileItemModel> downloadedList = new ArrayList<>(mItems);
        for(Listener listener:getListeners()){
            listener.notifySuccessfetchingFiles(downloadedList);
        }


    }



}
