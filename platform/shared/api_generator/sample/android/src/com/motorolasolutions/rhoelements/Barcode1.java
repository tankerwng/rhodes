package com.motorolasolutions.rhoelements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.motorolasolutions.rhoelements.barcode1.Barcode1Impl;

class Barcode1 {
    private static String sDefaultID;
    private static Map<String, IBarcode1> sActiveBarcodes = new LinkedHashMap<String, IBarcode1>();

    public static IBarcode1 create(final String id) {
        IBarcode1 res = sActiveBarcodes.get(id);
        if (res == null) {
            res = new Barcode1Impl(id);
            sActiveBarcodes.put(id, res);
        }
        return res;
    }

    private static List<String> getIds() {
        List<String> ids = new LinkedList<String>();
        ids.add("SCN1");
        return ids;
    }

    public static IBarcode1 getDefault() {
        if (sDefaultID == null) {
            sDefaultID = getIds().get(0);
        }
        return create(sDefaultID);
    }

    public static void setDefaultID(final String id) {
        create(id);
        sDefaultID = id;
    }

    public static IMethodResult enumerate() {
        IMethodResult res = new MethodResult();
        res.set(getIds());
        return res;
    }

    class GetPropsTask implements Runnable {
        private IBarcode1 mBarcode;
        private IMethodResult mResult;

        public GetPropsTask(IBarcode1 barcode, IMethodResult result) {
            mBarcode = barcode;
            mResult = result;
        }

        @Override
        public void run() {
            mBarcode.getProps(mResult);
        }
    }

    class GetProps1Task implements Runnable {
        private IBarcode1 mBarcode;
        private String mName;
        private IMethodResult mResult;

        public GetProps1Task(IBarcode1 barcode, String name, IMethodResult result) {
            mBarcode = barcode;
            mName = name;
            mResult = result;
        }

        @Override
        public void run() {
            mBarcode.getProps(mName, mResult);
        }
    }

    class GetProps2Task implements Runnable {
        private IBarcode1 mBarcode;
        private List<String> mNames;
        private IMethodResult mResult;

        public GetProps2Task(IBarcode1 barcode, List<String> names, IMethodResult result) {
            mBarcode = barcode;
            mNames = names;
            mResult = result;
        }

        @Override
        public void run() {
            mBarcode.getProps(mNames, mResult);
        }
    }
}