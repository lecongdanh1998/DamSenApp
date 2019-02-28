package vn.edu.poly.damsenapp.Presenter.UpdaterInfomation;

public interface UpdaterInformationPresenter {

    void updateInfo(String name, String email, String phone, String password, String cofimrPass);

    void onDestroy();
}
