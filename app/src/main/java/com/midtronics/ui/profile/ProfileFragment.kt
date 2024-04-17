package com.midtronics.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.midtronics.data.Profile
import com.midtronics.databinding.FragmentProfileBinding
import com.midtronics.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import java.io.IOException
import java.io.InputStream

class ProfileFragment : BaseFragment<Profile>() {

    private val profileViewModel: ProfileViewModel by inject()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val adapter = CvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.experienceList.layoutManager = LinearLayoutManager(context)
        binding.experienceList.adapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.getData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getViewModel() = profileViewModel

    override fun getLoaderView() = binding.loader

    override fun getRootView() = binding.root

    override fun onSuccess(data: Profile) {
        binding.name.text = "${data.firstName} ${data.lastName}"
        data.avatar?.let { avatar ->
            setUpAvatar(avatar)
        }

        adapter.updateData(
            experience = data.experience,
            education = data.education
        )
    }

    private fun setUpAvatar(avatar: String) {
        context?.let {context ->
            val assetManager = context.assets
            try {
                val inputStream: InputStream = assetManager.open(avatar)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.avatar.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}