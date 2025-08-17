import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  selectedOption: null,
  formData: {},
};

const adminSlice = createSlice({
  name: "admin",
  initialState,
  reducers: {
    selectOption: (state, action) => {
      state.selectedOption = action.payload;
    },
    submitForm: (state, action) => {
      state.formData = action.payload;
    },
  },
});

export const { selectOption, submitForm } = adminSlice.actions;
export default adminSlice.reducer;
