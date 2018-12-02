package com.antonio.mobilecodingtest.commons;

public class MVPContract {
    public interface View{
        void showError(String reason);
        void showProgress();
        void hideProgress();
    }
    public interface Presenter<T>{
        void onAttach(T view);
        void onDetach();
    }
    public interface Model{}
}
