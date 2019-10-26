package com.nemesiss.dev.ianime.InfrastructureExtension;

public interface TaskPostExecuteWrapper<TTaskReturn>
{
    void DoOnPostExecute(TTaskReturn TaskRet);
}
