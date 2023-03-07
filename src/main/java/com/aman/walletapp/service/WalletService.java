package com.aman.walletapp.service;

import com.aman.walletapp.entity.Wallet;
import com.aman.walletapp.exception.WalletException;
import com.aman.walletapp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getAll(){
        return walletRepository.findAllByOrderByPriority();
    }
    public Wallet getById(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()) {
            return wallet.get();
        }
        //TODO
        throw new WalletException("Wallet with "+id+" does not exist");
    }
    public Wallet createOrUpdate(Wallet wallet){
        if(wallet.getId()==null){
            walletRepository.save(wallet);
        }
        else{
            walletRepository.save(wallet);
        }
        return wallet;
    }

    public boolean delete(Long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isPresent()) {
            walletRepository.delete(wallet.get());
            return true;
        }
        //TODO
        throw new WalletException("Wallet with "+id+" does not exist");
    }
}
