package vn.magik.atmsach.util;

/**
 * Created by BlueSky on 11/25/2017.
 */
import java.util.ArrayList;
import java.util.List;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
public class CompareImage {
    private Context mContext;
    private static Bitmap bmp, bmpimg1, bmpimg2;
    private static int descriptor = DescriptorExtractor.SIFT;//BRIEF,BRISK,FREAK,ORB,SIFT,SURF
    private static int min_dist = 10;
    private static double max_percent=50;
    private static int min_matches = 500;
    public static CompareImage compare=null;

    public CompareImage(Context context){
        mContext=context;
    }

    public static CompareImage getInst(Context context){
        if(compare==null){
            compare=new CompareImage(context);
        }
        return compare;
    }


    public Integer run(Bitmap bmpimg1,Bitmap bmpimg2) {
        Integer temp=3;
        if (bmpimg1 != null && bmpimg2 != null) {
					/*if(bmpimg1.getWidth()!=bmpimg2.getWidth()){
						bmpimg2 = Bitmap.createScaledBitmap(bmpimg2, bmpimg1.getWidth(), bmpimg1.getHeight(), true);
					}*/
            bmpimg1 = Bitmap.createScaledBitmap(bmpimg1, 100, 100, true);
            bmpimg2 = Bitmap.createScaledBitmap(bmpimg2, 100, 100, true);
            Mat img1 = new Mat();
            Utils.bitmapToMat(bmpimg1, img1);
            Mat img2 = new Mat();
            Utils.bitmapToMat(bmpimg2, img2);
            Imgproc.cvtColor(img1, img1, Imgproc.COLOR_RGBA2GRAY);
            Imgproc.cvtColor(img2, img2, Imgproc.COLOR_RGBA2GRAY);
            img1.convertTo(img1, CvType.CV_32F);
            img2.convertTo(img2, CvType.CV_32F);
            //Log.d("ImageComparator", "img1:"+img1.rows()+"x"+img1.cols()+" img2:"+img2.rows()+"x"+img2.cols());
            Mat hist1 = new Mat();
            Mat hist2 = new Mat();
            MatOfInt histSize = new MatOfInt(180);
            MatOfInt channels = new MatOfInt(0);
            ArrayList<Mat> bgr_planes1= new ArrayList<Mat>();
            ArrayList<Mat> bgr_planes2= new ArrayList<Mat>();
            Core.split(img1, bgr_planes1);
            Core.split(img2, bgr_planes2);
            MatOfFloat histRanges = new MatOfFloat (0f, 180f);
            boolean accumulate = false;
            Imgproc.calcHist(bgr_planes1, channels, new Mat(), hist1, histSize, histRanges, accumulate);
            Core.normalize(hist1, hist1, 0, hist1.rows(), Core.NORM_MINMAX, -1, new Mat());
            Imgproc.calcHist(bgr_planes2, channels, new Mat(), hist2, histSize, histRanges, accumulate);
            Core.normalize(hist2, hist2, 0, hist2.rows(), Core.NORM_MINMAX, -1, new Mat());
            img1.convertTo(img1, CvType.CV_32F);
            img2.convertTo(img2, CvType.CV_32F);
            hist1.convertTo(hist1, CvType.CV_32F);
            hist2.convertTo(hist2, CvType.CV_32F);

            double compare = Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CHISQR);
            Log.d("ImageComparator", "compare: "+compare);
            if(compare>6000 && compare<8000) {
                temp=1;
            }
            else if(compare<=6000)
                temp=0;
            else
                temp=2;
        }
        return temp;
    }
//    public static class asyncTask extends AsyncTask<Void, Void, Void> {
//        private static Mat img1, img2, descriptors, dupDescriptors;
//        private static FeatureDetector detector;
//        private static DescriptorExtractor DescExtractor;
//        private static DescriptorMatcher matcher;
//        private static MatOfKeyPoint keypoints, dupKeypoints;
//        private static MatOfDMatch matches, matches_final_mat;
//        private static double percent_last;
//        private static ProgressDialog pd;
//        private boolean isDuplicate = false;
//        private Context asyncTaskContext=null;
//        private static Scalar RED = new Scalar(255,0,0);
//        private static Scalar GREEN = new Scalar(0,255,0);
//        public asyncTask(Context context)
//        {
//            asyncTaskContext=context;
//        }
//
//        public boolean getisDuplicate(){
//            return isDuplicate;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            pd = new ProgressDialog(asyncTaskContext);
//            pd.setIndeterminate(true);
//            pd.setCancelable(true);
//            pd.setCanceledOnTouchOutside(false);
//        }
//
//        @Override
//        protected Void doInBackground(Void... arg0) {
//            // TODO Auto-generated method stub
//            compare();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void result) {
//            try {
////                Mat img3 = new Mat();
////                MatOfByte drawnMatches = new MatOfByte();
////                Features2d.drawMatches(img1, keypoints, img2, dupKeypoints,
////                        matches_final_mat, img3, GREEN, RED,  drawnMatches, Features2d.NOT_DRAW_SINGLE_POINTS);
////                bmp = Bitmap.createBitmap(img3.cols(), img3.rows(),
////                        Bitmap.Config.ARGB_8888);
////                Imgproc.cvtColor(img3, img3, Imgproc.COLOR_BGR2RGB);
////                Utils.matToBitmap(img3, bmp);
//                List<DMatch> finalMatchesList = matches_final_mat.toList();
//
////                if (finalMatchesList.size() > min_matches)// dev discretion for
//                // number of matches to
//                // be found for an image
//                // to be judged as
//                // duplicate
//
//                if(percent_last<max_percent)
//                {
//                    isDuplicate = true;
//                } else {
//                    isDuplicate = false;
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(asyncTaskContext, e.toString(),
//                        Toast.LENGTH_LONG).show();
//            }
//        }
//
//        void compare() {
//            try {
//                bmpimg1 = bmpimg1.copy(Bitmap.Config.ARGB_8888, true);
//                bmpimg2 = bmpimg2.copy(Bitmap.Config.ARGB_8888, true);
//                img1 = new Mat();
//                img2 = new Mat();
//                Utils.bitmapToMat(bmpimg1, img1);
//                Utils.bitmapToMat(bmpimg2, img2);
//                Imgproc.cvtColor(img1, img1, Imgproc.COLOR_BGR2RGB);
//                Imgproc.cvtColor(img2, img2, Imgproc.COLOR_BGR2RGB);
//                detector = FeatureDetector.create(FeatureDetector.PYRAMID_FAST);
//                DescExtractor = DescriptorExtractor.create(descriptor);
//                matcher = DescriptorMatcher
//                        .create(DescriptorMatcher.BRUTEFORCE_HAMMING);
//
//                keypoints = new MatOfKeyPoint();
//                dupKeypoints = new MatOfKeyPoint();
//                descriptors = new Mat();
//                dupDescriptors = new Mat();
//                matches = new MatOfDMatch();
//                detector.detect(img1, keypoints);
//                Log.d("LOG!", "number of query Keypoints= " + keypoints.size());
//                detector.detect(img2, dupKeypoints);
//                Log.d("LOG!", "number of dup Keypoints= " + dupKeypoints.size());
//                // Descript keypoints
//                DescExtractor.compute(img1, keypoints, descriptors);
//                DescExtractor.compute(img2, dupKeypoints, dupDescriptors);
//                Log.d("LOG!", "number of descriptors= " + descriptors.size());
//                Log.d("LOG!",
//                        "number of dupDescriptors= " + dupDescriptors.size());
//                // matching descriptors
//                matcher.match(descriptors, dupDescriptors, matches);
//                Log.d("LOG!", "Matches Size " + matches.size());
//                // New method of finding best matches
//                List<DMatch> matchesList = matches.toList();
//                List<DMatch> matches_final = new ArrayList<DMatch>();
//                for (int i = 0; i < matchesList.size(); i++) {
//                    if (matchesList.get(i).distance <= min_dist) {
//                        matches_final.add(matches.toList().get(i));
//                    }
//                }
//                percent_last=(matches_final.size()/matchesList.size())*100;
//                matches_final_mat = new MatOfDMatch();
//                matches_final_mat.fromList(matches_final);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
}

