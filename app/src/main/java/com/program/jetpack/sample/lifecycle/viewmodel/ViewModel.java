package com.program.jetpack.sample.lifecycle.viewmodel;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 疑问点：ViewModel怎么与对象（Activity，Fragment）关联？
 * ViewModel的构建，注意不能直接new对象。因为直接new对象不会与Activity或Fragment对象关联
 *   1.使用ViewModelProvider构建
 *   new ViewModelProvider(this).get(类名::class.java)
 *   2.使用viewModels方法构建
 *   val viewModel: SampleRoomViewModel by viewModels {
 *                         SampleRoomViewModelFactory((application as SampleApplication).useRepository)
 *                     }
 */
public abstract class ViewModel {

    @Nullable
    private final Map<String, Object> mBagOfTags = new HashMap<>();
    @Nullable
    private final Set<Closeable> mCloseables = new LinkedHashSet<>();
    private volatile boolean mCleared = false;

    public ViewModel() {
    }

    public ViewModel(@NonNull Closeable... closeables) {
        mCloseables.addAll(Arrays.asList(closeables));
    }

    public void addCloseable(@NonNull Closeable closeable) {
        if (mCloseables != null) {
            synchronized (mCloseables) {
                mCloseables.add(closeable);
            }
        }
    }

    protected void onCleared() {

    }

    /**
     * 为什么限制在主线程？
     */
    @MainThread
    final void clear() {
        mCleared = true;
        // Since clear() is final, this method is still called on mock objects
        // and in those cases, mBagOfTags is null. It'll always be empty though
        // because setTagIfAbsent and getTag are not final so we can skip
        // clearing it
        if (mBagOfTags != null) {
            synchronized (mBagOfTags) {
                for (Object value : mBagOfTags.values()) {
                    // see comment for the similar call in setTagIfAbsent
                    closeWithRuntimeException(value);
                }
            }
        }
        // We need the same null check here
        if (mCloseables != null) {
            synchronized (mCloseables) {
                for (Closeable closeable : mCloseables) {
                    closeWithRuntimeException(closeable);
                }
            }
        }

        onCleared();
    }

    public <T> T setTagIfAbsent(String key, T newValue) {
        T previous;
        synchronized (mBagOfTags) {
            previous = (T) mBagOfTags.get(key);
            if (previous == null) {
                mBagOfTags.put(key, newValue);
            }
        }
        T result = previous == null ? newValue : previous;
        if (mCleared) {
            // It is possible that we'll call close() multiple times on the same object, but
            // Closeable interface requires close method to be idempotent:
            // "if the stream is already closed then invoking this method has no effect." (c)
            closeWithRuntimeException(result);
        }
        return result;
    }


    public <T> T getTag(String key) {
        if (mBagOfTags != null) {
            synchronized (mBagOfTags) {
                return (T)mBagOfTags.get(key);
            }
        }
        return null;
    }

    public void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
