package com.alejandrodcardona.springsecurity.backend.dto.facebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacebookPicture {

    private PictureData data;

}