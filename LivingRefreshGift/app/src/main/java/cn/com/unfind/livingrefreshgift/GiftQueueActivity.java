package cn.com.unfind.livingrefreshgift;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;

/**
 * @author zhongxf
 * @Description
 * @Date 2016/6/6.
 */
public class GiftQueueActivity extends Activity {
    private SurfaceView surfaceView;//后面的SurfaceView

    private LinearLayout giftCon;//礼物的里列表的外层ViewGroup
    private int SCREEN_WITH = 0;//屏幕的宽度
    private int SCREEN_HEIGHT = 0;//屏幕的高度


    private GiftShowManager giftManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        SCREEN_WITH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        surfaceView.getHolder().addCallback(new LivingHolderCallBack());
        giftCon = (LinearLayout) findViewById(R.id.gift_con);


        giftManger = new GiftShowManager(GiftQueueActivity.this, giftCon);

        giftManger.showGift();//开始显示礼物
        new Thread(new GetGiftRunnable()).start();//启动线程获取礼物的Vo


    }

    //线往队列中加入礼物
    private class GetGiftRunnable implements Runnable {
        @Override
        public void run() {
            GiftVo vo = new GiftVo();
            vo.setUserId("unfind");
            vo.setNum(1);
            giftManger.addGift(vo);
            GiftVo vo2 = new GiftVo();
            vo2.setUserId("unfind");
            vo2.setNum(1);
            giftManger.addGift(vo2);

            GiftVo vo3 = new GiftVo();
            vo3.setUserId("zhong");
            vo3.setNum(1);
            giftManger.addGift(vo3);
            GiftVo vo4 = new GiftVo();
            vo4.setUserId("xiao");
            vo4.setNum(1);
            giftManger.addGift(vo4);
            GiftVo vo5 = new GiftVo();
            vo5.setUserId("xiao");
            vo5.setNum(1);
            giftManger.addGift(vo5);
            GiftVo vo6 = new GiftVo();
            vo6.setUserId("xiao");
            vo6.setNum(1);
            giftManger.addGift(vo6);

        }
    }


    //SurfaceView绘制直播的界面的子类
    private class LivingHolderCallBack implements SurfaceHolder.Callback {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Canvas canvas = holder.lockCanvas();

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
            RectF rectF = new RectF(0, 0, SCREEN_WITH, SCREEN_HEIGHT);   //w和h分别是屏幕的宽和高，也就是你想让图片显示的宽和高
            canvas.drawBitmap(bm, null, rectF, null);
            holder.unlockCanvasAndPost(canvas);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }

}
