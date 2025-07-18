import streamlit as st
import tensorflow as tf
from PIL import Image
import numpy as np
import os

# Set page configuration
st.set_page_config(
    page_title="Skin Cancer Classification",
    page_icon="🔬",
    layout="wide"
)

# Define class names in order of label indices
class_names = [
    'Actinic keratoses',
    'Basal cell carcinoma',
    'Benign keratosis-like lesions',
    'Dermatofibroma',
    'Melanocytic nevi',
    'Melanoma',
    'Vascular lesions'
    
]

# function to load the model
@st.cache_resource
def load_model():
    try:
        model = tf.keras.models.load_model('skin_cancer_cnn_model.h5')
        return model
    except Exception as e:
        st.error(f"Error loading model: {str(e)}") #show error if model loading fails
        return None

# Preprocess a single uploaded image
def preprocess_single_image(image, target_size=(128, 128)):
    img = image.convert("RGB")  # Convert to RGB
    img = img.resize(target_size)  # Resize to model input size
    img_array = np.asarray(img, dtype=np.float32) / 255.0  # Normalizing the pixel values
    img_array = np.expand_dims(img_array, axis=0)  # Add batch dimension (1,128,128,3)
    return img_array

def main():
    st.title("🔬 Skin Cancer Classification")
    st.markdown("Upload an image to classify the type of skin lesion using our trained CNN model.")
   
   #loading the model
    model = load_model()
    if model is None:
        return
   
    # Layout dividing the page into 2 colomns
    col1, col2 = st.columns([1, 1])
   
   #column 1
    with col1:
        st.header("Upload Image")
        uploaded_file = st.file_uploader(
            "Choose a skin lesion image (JPG, PNG)",
            type=['jpg', 'jpeg', 'png']
        )

        if uploaded_file is not None:
            image = Image.open(uploaded_file)
            st.image(image, caption="Uploaded Image", use_container_width=True)
           
            if st.button("Classify Image", type="primary"):
                with st.spinner("Classifying..."):
                    processed_image = preprocess_single_image(image) #preprocess image
                    predictions = model.predict(processed_image) #predict
                    predicted_class_index = int(np.argmax(predictions)) # get index
                    predicted_class_name = class_names[predicted_class_index] #map index to class name
                    predicted_confidence = float(np.max(predictions)) #prediction confidence

                    #savng prediction results to the session state
                    st.session_state.prediction_results = {
                        'class_name': predicted_class_name,
                        'confidence': predicted_confidence,
                        'all_probs': predictions[0]
                    }

    #column 2
    with col2:
        st.header("Results")
        if 'prediction_results' in st.session_state:
            res = st.session_state.prediction_results

            #display predicted class and confidence score
            st.success(f"**Predicted Class:** {res['class_name']}") 
            st.info(f"**Confidence:** {res['confidence']:.2%}")

            #display probabilities of all classes from highest to least
            st.subheader("All Class Probabilities")
            sorted_indices = np.argsort(res['all_probs'])[::-1]
            for i in sorted_indices:
                label = class_names[i]
                prob = res['all_probs'][i]
                if i == sorted_indices[0]:
                    st.markdown(f"**{label}**: {prob:.2%}")
                else:
                    st.write(f"{label}: {prob:.2%}")

            st.warning("⚠️ This is a prototype and not for clinical use.")


    #Just some extra info
    with st.expander("ℹ️ About the Model"):
        st.write("""
        This tool uses a Convolutional Neural Network trained on the HAM10000 dataset
        to classify seven types of skin lesions. Results may not generalize to non-dermoscopic images.

        **Classes:**
        - Melanocytic nevi
        - Melanoma
        - Benign keratosis-like lesions
        - Basal cell carcinoma
        - Actinic keratoses
        - Vascular lesions
        - Dermatofibroma
        """)

if __name__ == "__main__":
    main()
