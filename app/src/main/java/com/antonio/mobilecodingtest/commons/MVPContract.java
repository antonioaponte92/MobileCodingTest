package com.antonio.mobilecodingtest.commons;

public class MVPContract {
    public interface View{
        void showNoResult();
        void showError(String reason);
    }
    public interface Presenter<T>{
        void onAttach(T view);
        void onDetach();
    }
    public interface Model{}
}
