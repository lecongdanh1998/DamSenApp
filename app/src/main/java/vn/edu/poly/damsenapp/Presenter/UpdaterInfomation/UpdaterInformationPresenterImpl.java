package vn.edu.poly.damsenapp.Presenter.UpdaterInfomation;

import vn.edu.poly.damsenapp.Model.UpdaterInfomation.UpdaterInformationModel;
import vn.edu.poly.damsenapp.View.UpdateInfomation.UpdateInformationView;

public class UpdaterInformationPresenterImpl implements UpdaterInformationPresenter, UpdaterInformationModel.OnUpdateListener {

    private UpdaterInformationModel mUpdateModel;
    private UpdateInformationView mUpdateView;

    public UpdaterInformationPresenterImpl(UpdaterInformationModel mUpdateModel, UpdateInformationView mUpdateView) {
        this.mUpdateModel = mUpdateModel;
        this.mUpdateView = mUpdateView;
    }

    @Override
    public void updateInfo(int success, String message) {
        if (mUpdateView != null){
            mUpdateView.updateInformationSucess(success, message);
        }
    }

    @Override
    public void updateInfo(String name, String email, String phone, String password,String confirmPass) {
        if (mUpdateModel != null){
            mUpdateModel.updateUser(this, name, email, phone, password, confirmPass);
        }
    }

    @Override
    public void onDestroy() {

    }
}
