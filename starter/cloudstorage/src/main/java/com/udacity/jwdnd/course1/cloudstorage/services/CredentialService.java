package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.viewmodels.CredentialForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper)
    {
        this.credentialMapper = credentialMapper;
    }

    public List<CredentialForm> getAllCredentials(Integer userId)
    {
        List<Credential> data = userId!= null ? this.credentialMapper.selectAll(userId) : this.credentialMapper.listAll();
        if(data == null)
            return null;
        var collected  = data.stream().map((x)->
                fromCredential(x)).collect(Collectors.toList());

        return collected;
    }


    public Integer addCredential(CredentialForm form)
    {
        Credential cred = fromCredentialForm(form);
        this.credentialMapper.insert(cred);
        return cred.getCredentialid();
    }

    public Integer updateCredential(CredentialForm form)
    {
        Credential cred = fromCredentialForm(form);
        return this.credentialMapper.update(cred);
    }

    public Integer deleteCredential(Integer cred, Integer user)
    {
        return this.credentialMapper.delete(cred,user);
    }

    public Credential fromCredentialForm(CredentialForm form)
    {
        Credential cred = new Credential();
        cred.setCredentialid(form.getCredentialid());
        cred.setPassword(form.getPassword());
        cred.setUrl(form.getUrl());
        cred.setUsername(form.getUsername());
        cred.setUserId(form.getUserId());

        return cred;
    }
    public CredentialForm fromCredential(Credential x)
    {
        return new CredentialForm(x.getCredentialid(),
                x.getUrl(),x.getUsername(),x.getPassword(),x.getUserId());
    }

}
