package diiage.potherat.demo.demoapp3.ui.vehicule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.databinding.FragmentVehiculeBinding;

@AndroidEntryPoint
public class VehiculeFragment extends Fragment {

    @Inject
    VehiculeViewModel viewModel;
    private FragmentVehiculeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVehiculeBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(VehiculeViewModel.class);

    }

    @Override
    public void onStart() {
        super.onStart();
        ready();
    }

    private void ready(){
        if (binding != null && viewModel != null){
            binding.setLifecycleOwner(getViewLifecycleOwner());
            binding.setViewmodel(viewModel);
            binding.btnValid.setOnClickListener(view -> {

                viewModel.searchVehicule();
            });

            viewModel.getVehicule().observe(getViewLifecycleOwner(), vehicule -> {
                if(vehicule != null){
                    binding.vehiculeFalse.setVisibility(View.GONE);
                    binding.vehiculeTrue.setVisibility(View.VISIBLE);
                    binding.txtModelV.setText(vehicule.model);
                    binding.txtName.setText(vehicule.name);
                }else {
                    binding.vehiculeFalse.setVisibility(View.VISIBLE);
                    binding.vehiculeTrue.setVisibility(View.GONE);

                }
            });

        }
    }
}
