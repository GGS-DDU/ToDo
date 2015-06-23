package com.xds.todo.activity;

import com.xds.todo.R;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class slideItemView extends RelativeLayout implements OnClickListener {

	private TextView content_view;
	private Button delBtn;
	private Button almBtn;

	private Context context;

	private int downX;

	public slideItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}

	public slideItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public slideItemView(Context context) {
		this(context, null);
	}

	private void initView(Context context) {
		this.context = context;
		View v = LayoutInflater.from(context).inflate(R.layout.item_view, this);

		content_view = (TextView) v.findViewById(R.id.content_view);
		delBtn = (Button) v.findViewById(R.id.delete_view);
		almBtn = (Button) v.findViewById(R.id.alarm_view);

		content_view.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return handleTouch(v, event);
			}
		});
		content_view.setOnClickListener(this);
		delBtn.setOnClickListener(this);
		almBtn.setOnClickListener(this);
	}

	boolean result = false;// true:允许移动，阻止点击
	boolean isOpen = false;// 判断是否已展开

	protected boolean handleTouch(View v, MotionEvent event) {
		int bottomWidth = delBtn.getWidth() + almBtn.getWidth();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			downX = (int) event.getRawX();
			break;
		case MotionEvent.ACTION_MOVE:

			int dx = (int) (event.getRawX() - downX);// 手指移动的距离
			if (isOpen) {// 打开状态
				if (dx > 0 && dx < bottomWidth) {
					v.setTranslationX(dx - bottomWidth);
					result = true;
				}
			} else {// 关闭状态
				if (dx < 0 && Math.abs(dx) <= bottomWidth) {
					v.setTranslationX(dx);
					result = true;
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:

			float ddx = v.getTranslationX();// 获取item已经移动的偏移量
			if (ddx < 0 && ddx > -(bottomWidth / 2)) {
				// 要关闭
				ObjectAnimator oa1 = ObjectAnimator.ofFloat(v, "translationX",
						ddx, 0).setDuration(100);// 可变参数的个数为2时，表明第一个是起始值，第二个是终止值
				oa1.start();
				oa1.addListener(new AnimatorListener() {

					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}

					// 动画结束时
					@Override
					public void onAnimationEnd(Animator animation) {
						isOpen = false;
						result = false;// 可以点击
					}

					// 动画取消时
					@Override
					public void onAnimationCancel(Animator animation) {
						isOpen = false;
						result = false;// 可以点击
					}
				});
			}
			// 要打开
			if (ddx <= -(bottomWidth / 2) && ddx >= -bottomWidth) {// 向左移动距离超过一个按钮的距离且不大于两个按钮的宽度
				ObjectAnimator oa1 = ObjectAnimator.ofFloat(v, "translationX",
						ddx, -bottomWidth).setDuration(100);// 可变参数的个数为2时，表明第一个是起始值，第二个是终止值
				oa1.start();
				result = true;// 可以点击
				isOpen = true;
			}
			break;
		}
		return result;// item展开时返回true，不可以点击，但两个按钮可以
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.content_view:
			Toast.makeText(context, "content data", Toast.LENGTH_SHORT).show();
			break;
		case R.id.delete_view:
			Toast.makeText(context, "delete data", Toast.LENGTH_SHORT).show();
			break;
		case R.id.alarm_view:
			Toast.makeText(context, "alarm data", Toast.LENGTH_SHORT).show();
			break;
		}
	}

}
