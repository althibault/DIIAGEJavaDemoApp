package diiage.potherat.demo.demoapp3.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import diiage.potherat.demo.demoapp3.R;
import diiage.potherat.demo.demoapp3.databinding.FragmentHomeBinding;
import diiage.potherat.demo.demoapp3.databinding.FragmentQuoteEditBinding;
import diiage.potherat.demo.demoapp3.ui.edit.QuoteEditViewModel;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    @Inject
    HomeViewModel viewModel;
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewmodel(viewModel);

        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this,getDefaultViewModelProviderFactory()).get(HomeViewModel.class);

    }



    @Override
    public void onStart() {
        super.onStart();
        viewModel.getCountQuotes().observe(getViewLifecycleOwner(), number -> {
            binding.txtNumberOfQuotes.setText(number);
        });
        viewModel.getCountSources().observe(getViewLifecycleOwner(), source -> {
            binding.txtDistinctSources.setText(source);
        });
        viewModel.getLastQuote().observe(getViewLifecycleOwner(), quote -> {
            binding.lastQuote.setModelQuote(quote);
        });

    }
}