package com.example.airal.paint.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.airal.paint.R;
import com.example.airal.paint.model.Record;

public class TouchImageView extends AppCompatImageView {

    private Context mContext;
    private Matrix currentMatrix, savedMatrix;// Matrix对象

    private PointF startF= new PointF();
    private PointF midF;// 起点、中点对象

    // 初始的两个手指按下的触摸点的距离
    private float oldDis = 1f;

    private float saveRotate = 0F;// 保存了的角度值

    private static final int MODE_NONE = 0;// 默认的触摸模式
    private static final int MODE_DRAG = 1;// 拖拽模式
    private static final int MODE_ZOOM = 2;// 缩放模式
    private static final int MODE_ROTATO = 3;// 旋转模式
    private int mode = MODE_NONE;

    public TouchImageView(Context contexts) {
        super(contexts);
        init();
    }

    public TouchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        // 初始化
        init();
    }

    private void init() {
        /*
         * 实例化对象
         */
        currentMatrix = new Matrix();
        savedMatrix = new Matrix();

        /*
         * 获取屏幕宽高
         */

        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width = outMetrics.heightPixels/5;
        int Screenheight = outMetrics.heightPixels;

        /*
         * 设置图片资源
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.duicheng);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, width, true);
        setImageBitmap(bitmap);
    }

    float moveX;
    float moveY;
    float x=0;
    float y=0;
    int time=0;
    Record record=null;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(myListener!=null){
            myListener.onItemClick(this);
        }
        switch (event.getAction()& MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:// 单点接触屏幕时
                moveX = event.getRawX();
                moveY = event.getRawY();
                if(MyApplication.isDrag==false){
                    if(calDis(event.getX(),event.getY())<getMeasuredHeight()/3){
                        mode=MODE_DRAG;
                        System.out.println("MODE_DRAG");
                    }else{
                        mode=MODE_ROTATO;
                        System.out.println("MODE_ROTATO");
                    }
                    record=new Record();
                    record.tag=Record.TAG_TOUCH;
                    record.view=this;
                    record.x=getX();
                    record.y=getY();
                    record.scale=getScaleX();
                    record.rotato=getRotation();
                    MyApplication.isDrag=true;
                }
                //计算初始的角度
                saveRotate = calRotation(event);
                System.out.println(saveRotate);
                time=0;
                //控件置于最上层
                bringToFront();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == MODE_ROTATO && event.getPointerCount() == 1 && myListener!=null) {
                    float rotate = calRotation(event);
                    System.out.println(event.getX()+" "+event.getY());
                    setRotation(getRotation()+rotate-saveRotate);
                    MyApplication.isDrag=true;
                }
                if (mode == MODE_DRAG&&event.getPointerCount() == 1) {
                    x = getX() + (event.getRawX() - moveX);
                    y = getY() + (event.getRawY() - moveY);
                    setX(x);
                    setY(y);
                    moveX = event.getRawX();
                    moveY = event.getRawY();
                    if(time==0&&record!=null){
                        if(myListener!=null){
                            myListener.onItemClick(this);
                            myListener.addRecord(record);
                            System.out.println("add record");
                        }
                        time++;
                    }
                    MyApplication.isDrag=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                // 单点离开屏幕时
                MyApplication.isDrag=false;
                mode=MODE_NONE;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                MyApplication.isDrag=true;
                // 第二个点离开屏幕时
                mode=MODE_NONE;
                break;
            default:
                break;
        }
        return true;
    }

    int width=0;
    public void setViewFrame(int new_width,int new_height,int old_width,int old_height)
    {
        ViewGroup.LayoutParams params=getLayoutParams();
        params.width=new_width;
        params.height=new_height;
        setX(getX()-new_width/2+old_width/2);
        setY(getY()-new_height/2+old_height/2);
        setLayoutParams(params);
    }

    public void setViewSize(float temp){
        temp=temp-1;
        int new_left,new_top,new_right,new_bottom;
        if (temp > 0)
        {
            new_left=getLeft() - (int) (temp * width/2);
            new_top=getTop() - (int) (temp * width/2);
            new_right=getRight() + (int) (temp * width/2);
            new_bottom=getBottom() + (int) (temp * width/2);
        }
        else
        {
            new_left=getLeft() + (int) (temp * width/2);
            new_top=getTop() + (int) (temp * width/2);
            new_right=getRight() - (int) (temp * width/2);
            new_bottom=getBottom() - (int) (temp * width/2);
        }
        setFrame(new_left,new_top,new_right,new_bottom);
//        ViewGroup.LayoutParams params=getLayoutParams();
//        params.width=new_right-new_left;
//        params.height=new_bottom-new_top;
//        setLayoutParams(params);
    }

    public void setImageListener(onImageClickListener myListener) {
        this.myListener = myListener;
    }

    public onImageClickListener myListener;

    public interface onImageClickListener{
        void onItemClick(View view);

        void addRecord(Record record);
    }

    // 计算两个触摸点之间的距离
    private float calDis(float posX,float posY) {
        float x =posX-getMeasuredWidth()/2;
        float y =posY-getMeasuredHeight()/2;
        return (float) Math.sqrt(x * x + y * y);
    }

    //计算角度
    private float calRotation(MotionEvent event) {
        double deltaX = event.getX()-getMeasuredWidth()/2;
        double deltaY = event.getY()-getMeasuredHeight()/2;
        double radius = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radius);
    }
}