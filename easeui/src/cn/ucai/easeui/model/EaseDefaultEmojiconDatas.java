package cn.ucai.easeui.model;

import cn.ucai.easeui.domain.EaseEmojicon;
import cn.ucai.easeui.domain.EaseEmojicon.Type;
import cn.ucai.easeui.utils.EaseSmileUtils;

public class EaseDefaultEmojiconDatas {
    
    private static String[] emojis = new String[]{
        EaseSmileUtils.ee_1,
        EaseSmileUtils.ee_2,
        EaseSmileUtils.ee_3,
        EaseSmileUtils.ee_4,
        EaseSmileUtils.ee_5,
        EaseSmileUtils.ee_6,
        EaseSmileUtils.ee_7,
        EaseSmileUtils.ee_8,
        EaseSmileUtils.ee_9,
        EaseSmileUtils.ee_10,
        EaseSmileUtils.ee_11,
        EaseSmileUtils.ee_12,
        EaseSmileUtils.ee_13,
        EaseSmileUtils.ee_14,
        EaseSmileUtils.ee_15,
        EaseSmileUtils.ee_16,
        EaseSmileUtils.ee_17,
        EaseSmileUtils.ee_18,
        EaseSmileUtils.ee_19,
        EaseSmileUtils.ee_20,
        EaseSmileUtils.ee_21,
        EaseSmileUtils.ee_22,
        EaseSmileUtils.ee_23,
        EaseSmileUtils.ee_24,
        EaseSmileUtils.ee_25,
        EaseSmileUtils.ee_26,
        EaseSmileUtils.ee_27,
        EaseSmileUtils.ee_28,
        EaseSmileUtils.ee_29,
        EaseSmileUtils.ee_30,
        EaseSmileUtils.ee_31,
        EaseSmileUtils.ee_32,
        EaseSmileUtils.ee_33,
        EaseSmileUtils.ee_34,
        EaseSmileUtils.ee_35,
       
    };
    
    private static int[] icons = new int[]{
        cn.ucai.easeui.R.drawable.ee_1,
        cn.ucai.easeui.R.drawable.ee_2,
        cn.ucai.easeui.R.drawable.ee_3,
        cn.ucai.easeui.R.drawable.ee_4,
        cn.ucai.easeui.R.drawable.ee_5,
        cn.ucai.easeui.R.drawable.ee_6,
        cn.ucai.easeui.R.drawable.ee_7,
        cn.ucai.easeui.R.drawable.ee_8,
        cn.ucai.easeui.R.drawable.ee_9,
        cn.ucai.easeui.R.drawable.ee_10,
        cn.ucai.easeui.R.drawable.ee_11,
        cn.ucai.easeui.R.drawable.ee_12,
        cn.ucai.easeui.R.drawable.ee_13,
        cn.ucai.easeui.R.drawable.ee_14,
        cn.ucai.easeui.R.drawable.ee_15,
        cn.ucai.easeui.R.drawable.ee_16,
        cn.ucai.easeui.R.drawable.ee_17,
        cn.ucai.easeui.R.drawable.ee_18,
        cn.ucai.easeui.R.drawable.ee_19,
        cn.ucai.easeui.R.drawable.ee_20,
        cn.ucai.easeui.R.drawable.ee_21,
        cn.ucai.easeui.R.drawable.ee_22,
        cn.ucai.easeui.R.drawable.ee_23,
        cn.ucai.easeui.R.drawable.ee_24,
        cn.ucai.easeui.R.drawable.ee_25,
        cn.ucai.easeui.R.drawable.ee_26,
        cn.ucai.easeui.R.drawable.ee_27,
        cn.ucai.easeui.R.drawable.ee_28,
        cn.ucai.easeui.R.drawable.ee_29,
        cn.ucai.easeui.R.drawable.ee_30,
        cn.ucai.easeui.R.drawable.ee_31,
        cn.ucai.easeui.R.drawable.ee_32,
        cn.ucai.easeui.R.drawable.ee_33,
        cn.ucai.easeui.R.drawable.ee_34,
        cn.ucai.easeui.R.drawable.ee_35,
    };
    
    
    private static final EaseEmojicon[] DATA = createData();
    
    private static EaseEmojicon[] createData(){
        EaseEmojicon[] datas = new EaseEmojicon[icons.length];
        for(int i = 0; i < icons.length; i++){
            datas[i] = new EaseEmojicon(icons[i], emojis[i], Type.NORMAL);
        }
        return datas;
    }
    
    public static EaseEmojicon[] getData(){
        return DATA;
    }
}
