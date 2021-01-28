package diiage.potherat.demo.demoapp3.ui.vehicule;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.common.Event;
import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.Quote;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class VehiculeViewModel extends ViewModel {

    SWRepository swRepository;
    ExecutorService executorService;
    private  MutableLiveData<String> id = new MutableLiveData<>();

    private  LiveData<Vehicle> vehicule = new MutableLiveData<>();


    @Inject
    @ViewModelInject
    public VehiculeViewModel(ExecutorService executorService, SWRepository swRepository){
        this.swRepository = swRepository;
        this.executorService = executorService;

    }

    public MutableLiveData<String> getId() {
        return id;
    }



    public void searchVehicule() {
        String value = this.id.getValue() != null ? this.id.getValue() : "";

        int idVehicule =  value.isEmpty() ? 0: Integer.parseInt(value);

        vehicule = Transformations.map(swRepository.getOneVehiculeById(idVehicule), input -> {
                    if(input instanceof ApiSuccessResponse) {
                        return ((ApiSuccessResponse<Vehicle>) input).getBody();
                    } else if (input instanceof ApiErrorResponse){
                        Log.e("debug",((ApiErrorResponse<Vehicle>) input).getErrorMessage()+"");
                    }
                    return null;
                }
        );
    }

    public LiveData<Vehicle> getVehicule() {
        return vehicule;
    }
}
